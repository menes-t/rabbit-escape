package rabbitescape.engine;

import static rabbitescape.engine.ChangeDescription.State.*;

import java.util.Map;

import rabbitescape.engine.ChangeDescription.State;

public class Exiting implements Behaviour
{
    @Override
    public State newState( Rabbit rabbit, World world )
    {
        for ( Thing thing : world.things )
        {
            if (
                   ( thing instanceof Exit )
                && ( thing.x == rabbit.x && thing.y == rabbit.y )
            )
            {
                return RABBIT_ENTERING_EXIT;
            }
        }
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state == RABBIT_ENTERING_EXIT )
        {
            world.changes.saveRabbit( rabbit );
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
    }
}
