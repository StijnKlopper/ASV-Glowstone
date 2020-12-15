package net.glowstone.ASV;

import net.glowstone.GlowServer;
import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.minecraft.SeedCommand;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SeedCommandTest extends CommandTest<SeedCommand> {

    protected CommandSender opPlayer;

    private GlowWorld world;

    private GlowServer server;

    @Before
    @Override
    public void before() {
        super.before();

        //arrange
        opPlayer = PowerMockito.mock(Player.class);
        world = PowerMockito.mock(GlowWorld.class);
        server = PowerMockito.mock(GlowServer.class);

        Mockito.when(opPlayer.getServer()).thenReturn(server);
        Mockito.when(opSender.getServer()).thenReturn(server);

        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);

    }
    
    public SeedCommandTest() {
        super(SeedCommand::new);
    }

    @Test
    public void executeSeedCommand() {
        //act & assert
        assertThat(command.execute(opSender, "label", new String[0]), is(true));
        Mockito.verify(world).getSeed();
    }
}

