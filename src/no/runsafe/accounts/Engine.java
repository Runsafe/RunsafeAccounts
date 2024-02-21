package no.runsafe.accounts;

import no.runsafe.accounts.repositories.AccountRepository;
import no.runsafe.framework.api.player.IPlayer;

import java.util.zip.Adler32;

public class Engine
{
	public Engine(AccountRepository repository)
	{
		this.repository = repository;
	}

	public String getNewAuthToken(IPlayer player)
	{
		String input = player.getName() + System.currentTimeMillis();

		Adler32 checksum = new Adler32();
		checksum.update(input.getBytes());
		String token = Integer.toHexString((int) checksum.getValue());

		this.repository.update(player, token);
		return token;
	}

	private final AccountRepository repository;
}
