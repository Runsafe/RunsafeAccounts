package no.runsafe.accounts;

import no.runsafe.accounts.commands.BLAH;
import no.runsafe.framework.RunsafePlugin;
import no.runsafe.framework.command.Command;

public class Plugin extends RunsafePlugin
{
	@Override
	protected void PluginSetup()
	{
		this.addComponent(Engine.class);

		Command account = new Command("account", "A collection of account management tools.", null);
		account.addSubCommand(getInstance(BLAH.class));
		this.addComponent(account);
	}
}