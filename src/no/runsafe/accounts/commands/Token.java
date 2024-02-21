package no.runsafe.accounts.commands;

import no.runsafe.accounts.Engine;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

public class Token extends PlayerCommand
{
	public Token(Engine engine)
	{
		super("token", "Generates a new account token", "runsafe.accounts.token");
		this.engine = engine;
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		executor.sendColouredMessage("&cDO NOT give your token to ANYONE under any circumstance.");
		executor.sendColouredMessage("&aRunning this command again will unlink your account again.");
		return "&3New account token: " + this.engine.getNewAuthToken(executor);
	}

	private final Engine engine;
}
