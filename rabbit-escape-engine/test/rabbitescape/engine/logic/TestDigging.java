package rabbitescape.engine.logic;

import static org.hamcrest.MatcherAssert.*;
import static rabbitescape.engine.Tools.*;
import static rabbitescape.engine.textworld.TextWorldManip.*;

import org.junit.Test;

import rabbitescape.engine.World;

public class TestDigging
{
    // TODO: slopes and bridges

    @Test
    public void Dig_through_single_floor()
    {
        World world = createWorld(
            "rd ",
            "###",
            "   ",
            "   ",
            "###"
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#D#",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#f#",
                " f ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                " r ",
                " f ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "   ",
                " r>",
                "###"
            )
        );
    }

    @Test
    public void Dig_through_multilevel_floor()
    {
        World world = createWorld(
            "rd ",
            "###",
            "###",
            "###",
            "   ",
            "   ",
            "###"
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#D#",
                "###",
                "###",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#f#",
                "###",
                "###",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "#r#",
                "#D#",
                "###",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "#r#",
                "#f#",
                "###",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "#r#",
                "#D#",
                "   ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "#r#",
                "#f#",
                " f ",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "# #",
                "# #",
                " r ",
                " f ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "# #",
                "# #",
                "   ",
                " r>",
                "###"
            )
        );
    }

    @Test
    public void Stop_after_single_gap()
    {
        World world = createWorld(
            "rd ",
            "###",
            "   ",
            "###"
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#D#",
                "   ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#f#",
                " f ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                " r>",
                "###"
            )
        );
    }

    @Test
    public void Stop_after_single_gap_after_multilevel_dig()
    {
        World world = createWorld(
            "rd ",
            "###",
            "###",
            "###",
            "   ",
            "###"
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                " r ",
                "#D#",
                "###",
                "###",
                "   ",
                "###"
            )
        );

        world.step();
        world.step();
        world.step();
        world.step();
        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "#r#",
                "#f#",
                " f ",
                "###"
            )
        );

        world.step();

        assertThat(
            renderWorld( world, true, false ),
            equalTo(
                "   ",
                "# #",
                "# #",
                "# #",
                " r>",
                "###"
            )
        );
    }
}