package no.runsafe.accounts.repositories;

import no.runsafe.framework.api.database.IDatabase;
import no.runsafe.framework.api.database.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class AccountRepository extends Repository
{
	public AccountRepository(IDatabase database)
	{
		this.database = database;
	}

	@Override
	public String getTableName()
	{
		return "runsafe_accounts_tokens";
	}

	public void update(String playerName, String token)
	{
		this.database.Execute(
				"INSERT INTO `runsafe_account_tokens` (playerName, token) VALUES(?, ?) " +
					"ON DUPLICATE KEY UPDATE token = ?",
				playerName, token, token
		);

		this.database.Execute(
			"DELETE FROM `runsafe_account_links` WHERE PlayerName = ?", playerName
		);
	}

	@Override
	public HashMap<Integer, List<String>> getSchemaUpdateQueries()
	{
		HashMap<Integer, List<String>> versions = new LinkedHashMap<Integer, List<String>>();
		ArrayList<String> sql = new ArrayList<String>();
		sql.add(
				"CREATE TABLE `runsafe_account_tokens` (" +
						"`playerName` varchar(50) NOT NULL," +
						"`token` varchar(8) NOT NULL," +
						"PRIMARY KEY(`playerName`)" +
				")"
		);
		versions.put(1, sql);
		return versions;
	}

	private IDatabase database;
}
