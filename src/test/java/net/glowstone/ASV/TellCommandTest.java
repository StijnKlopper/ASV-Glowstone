package net.glowstone.ASV;

import net.glowstone.GlowServer;
import net.glowstone.GlowWorld;
import net.glowstone.command.CommandTest;
import net.glowstone.command.CommandUtils;
import net.glowstone.command.minecraft.TellCommand;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TellCommandTest extends CommandTest<TellCommand> {

    protected CommandSender opPlayer;

    private GlowWorld world;

    private GlowServer server;


    @Before
    @Override
    public void before() {
        super.before();
        opPlayer = PowerMockito.mock(Player.class);
        world = PowerMockito.mock(GlowWorld.class);
        server = PowerMockito.mock(GlowServer.class);

        Mockito.when(opPlayer.getServer()).thenReturn(server);
        Mockito.when(opSender.getServer()).thenReturn(server);

        PowerMockito.stub(PowerMockito.method(CommandUtils.class, "getWorld", CommandSender.class)).toReturn(world);
    }
    
    public TellCommandTest() {
        super(TellCommand::new);
    }

    @Test
    public void executeStringValue() {
        String args[] = new String[1];
        args[0] = "hard";

       /* assertThat(command.execute(opSender, "label", args), is(true));
        Mockito.verify(opSender).send(sender, "Tarik", "Stijn", message);
        senderMessage.send(sender, senderName, player.getName(), message);*/
    }
}

