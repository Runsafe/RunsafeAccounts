package no.runsafe.accounts;

import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.zip.Adler32;

public class Engine
{
	public String getNewAuthToken(RunsafePlayer player)
	{
		Adler32 checksum = new Adler32();
		String input = player.getName() + System.currentTimeMillis();
		checksum.update(input.getBytes());

		return Integer.toHexString((int) checksum.getValue());
	}
}
