package no.runsafe.accounts.repositories;

import no.runsafe.framework.api.database.IDatabase;
import no.runsafe.framework.api.database.ISchemaUpdate;
import no.runsafe.framework.api.database.Repository;
import no.runsafe.framework.api.database.SchemaUpdate;
import no.runsafe.framework.api.player.IPlayer;

public class AccountRepository extends Repository
{
	@Override
	public String getTableName()
	{
		return "runsafe_accounts_tokens";
	}

	public void update(IPlayer player, String token)
	{
		this.database.execute(
			"INSERT INTO `runsafe_account_tokens` (playerName, token) VALUES(?, ?) " +
				"ON DUPLICATE KEY UPDATE token = ?",
			player.getName(), token, token
		);

		this.database.execute(
			"DELETE FROM `runsafe_account_links` WHERE playerName = ?", player.getName()
		);
	}

	@Override
	public ISchemaUpdate getSchemaUpdateQueries()
	{
		ISchemaUpdate update = new SchemaUpdate();

		update.addQueries(
			"CREATE TABLE `runsafe_account_tokens` (" +
				"`playerName` varchar(50) NOT NULL," +
				"`token` varchar(8) NOT NULL," +
				"PRIMARY KEY(`playerName`)" +
			")"
		);

		return update;
	}
}
