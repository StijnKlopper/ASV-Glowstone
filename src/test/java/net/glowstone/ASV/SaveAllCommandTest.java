package net.glowstone.ASV;

import net.glowstone.GlowServer;
import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.minecraft.SaveAllCommand;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;

public class SaveAllCommandTest extends CommandTest<SaveAllCommand> {

    protected CommandSender opPlayer;

    private GlowWorld world;

    private GlowServer server;
    private List<World> worlds;
    public SaveAllCommandTest() {
        super(SaveAllCommand::new);
    }


    @Before
    @Override
    public void before() {
        super.before();
        opPlayer = PowerMockito.mock(Player.class);
        world = PowerMockito.mock(GlowWorld.class);
        server = PowerMockito.mock(GlowServer.class);

        worlds = new ArrayList<World>();
        worlds.add(world);
        worlds.add(world);
        worlds.add(world);

        Mockito.when(opPlayer.getServer()).thenReturn(server);
        Mockito.when(server.getWorlds()).thenReturn(worlds);
        Mockito.when(opSender.getServer()).thenReturn(server);

        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);
    }

    @Test
    public void testExecute() {


        assertThat(command.execute(opSender, "label", new String[2]), is(true));
        Mockito.verify(world, times(3)).save();
    }
}

