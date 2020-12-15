package net.glowstone.ASV;

import net.glowstone.GlowServer;
import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.LocalizedEnumNames;
import net.glowstone.command.minecraft.DifficultyCommand;
import org.bukkit.Difficulty;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class DifficultyCommandTest extends CommandTest<DifficultyCommand> {

    protected CommandSender opPlayer;

    private GlowWorld world;

    private GlowServer server;

    
    public DifficultyCommandTest() {
        super(DifficultyCommand::new);
    }

    @Before
    @Override
    public void before() {
        super.before();

        // arrange
        world = PowerMockito.mock(GlowWorld.class);
        server = PowerMockito.mock(GlowServer.class);

        Mockito.when(opSender.getServer()).thenReturn(server);
        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);
    }


    @Test
    public void executeNumber() {
        // act
        String args[] = new String[1];
        args[0] = "0";

        // assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setDifficulty(Difficulty.PEACEFUL);
    }

    @Test
    public void executeStringValue() {
        //act
        String args[] = new String[1];
        args[0] = "hard";

        // assert
        assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(world).setDifficulty(Difficulty.HARD);
    }
}

