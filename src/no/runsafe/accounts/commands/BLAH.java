package no.runsafe.accounts.commands;

import no.runsafe.accounts.Engine;
import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class BLAH extends PlayerCommand
{
	public BLAH(Engine engine)
	{
		super("token", "Generates a new account token", "runsafe.accounts.token");
		this.engine = engine;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		executor.sendColouredMessage("&cDO NOT give your token to ANYONE under any circumstance.");
		return "&3New account token: " + this.engine.getNewAuthToken(executor);
	}

	private Engine engine;
}
