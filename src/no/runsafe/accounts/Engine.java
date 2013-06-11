package no.runsafe.accounts;

import no.runsafe.accounts.repositories.AccountRepository;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.zip.Adler32;

public class Engine
{
	public Engine(AccountRepository repository)
	{
		this.repository = repository;
	}

	public String getNewAuthToken(RunsafePlayer player)
	{
		String playerName = player.getName();
		String input = playerName + System.currentTimeMillis();

		Adler32 checksum = new Adler32();
		checksum.update(input.getBytes());
		String token = Integer.toHexString((int) checksum.getValue());

		this.repository.update(playerName, token);
		return token;
	}

	private AccountRepository repository;
}
