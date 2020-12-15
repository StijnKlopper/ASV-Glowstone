package net.glowstone.ASV;

import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.minecraft.TimeCommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TimeCommandTest extends CommandTest<TimeCommand> {

    protected CommandSender opPlayer;

    private GlowWorld world;

    public TimeCommandTest() {
        super(TimeCommand::new);
    }

    @Before
    @Override
    public void before() {
        super.before();

        world = PowerMockito.mock(GlowWorld.class);

        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);
    }

    @Test
    public void testTimeDayCommand() {
        String[] args = new String[2];
        args[0] = "set";
        args[1] = "day";

        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setTime(1000);
    }

    @Test
    public void testTimeNightCommand() {
        String[] args = new String[2];
        args[0] = "set";
        args[1] = "night";

        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setTime(13000);
    }

    @Test
    public void testTimeValueCommand() {
        String[] args = new String[2];
        args[0] = "add";
        args[1] = "250";
        Mockito.when(world.getTime()).thenReturn(200l);


        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setTime(450);
    }
}

