package net.glowstone.ASV;

import net.glowstone.GlowServer;
import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.minecraft.WeatherCommand;
import net.glowstone.util.TickUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WeatherCommandTest extends CommandTest<WeatherCommand> {

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
        Mockito.when(world.getSeed()).thenReturn(100001l);

        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);

    }
    
    public WeatherCommandTest() {
        super(WeatherCommand::new);
    }


    @Test
    public void executeClearCommand() {
        //act
        String args[] = new String[1];
        args[0] = "clear";

        //assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setThundering(false);
        Mockito.verify(world).setStorm(false);
    }

    @Test
    public void executeThunderCommand() {
        //act
        String args[] = new String[1];
        args[0] = "thunder";

        //assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setThundering(true);
        Mockito.verify(world).setStorm(true);
    }

    @Test
    public void executeRainCommand() {
        //act
        String args[] = new String[1];
        args[0] = "rain";

        //assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setStorm(true);
        Mockito.verify(world).setThundering(false);
    }

    @Test
    public void executeDurationCommand() {
        //act
        String args[] = new String[2];
        args[0] = "rain";
        args[1] = "1000";

        //assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setStorm(true);
        Mockito.verify(world).setThundering(false);
        Mockito.verify(world).setWeatherDuration(TickUtil.secondsToTicks(1000));
    }

}

