package no.runsafe.accounts.repositories;

import no.runsafe.framework.api.database.ISchemaUpdate;
import no.runsafe.framework.api.database.Repository;
import no.runsafe.framework.api.database.SchemaUpdate;
import no.runsafe.framework.api.player.IPlayer;

import javax.annotation.Nonnull;

public class AccountRepository extends Repository
{
	@Nonnull
	@Override
	public String getTableName()
	{
		return "runsafe_accounts_tokens";
	}

	public void update(IPlayer player, String token)
	{
		this.database.execute(
			"INSERT INTO `runsafe_account_tokens` (playerName, token, playerID) VALUES(?, ?, ?) " +
				"ON DUPLICATE KEY UPDATE token = ?",
			player.getName(), token, player, token
		);

		this.database.execute(
			"DELETE FROM `runsafe_account_links` WHERE playerName = ?", player.getName()
		);
	}

	@Nonnull
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

		update.addQueries(
			String.format("ALTER TABLE `%s` ADD COLUMN playerID VARCHAR(36) NOT NULL", getTableName()),
			String.format( // Player names -> Unique IDs
				"UPDATE IGNORE `%s` SET `playerID` = " +
					"COALESCE((SELECT `uuid` FROM player_db WHERE `name`=`%s`.`playerName`), `playerName`) " +
					"WHERE length(`playerID`) != 36",
				getTableName(), getTableName()
			)
		);

		return update;
	}
}
