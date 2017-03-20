package rabbitescape.engine.textworld;

import static rabbitescape.engine.Direction.*;

import java.util.List;

import rabbitescape.engine.ChangeDescription.State;
import rabbitescape.engine.Rabbit;

public class RabbitRenderer
{
    public static void render( Chars chars, List<Rabbit> rabbits )
    {
        for ( Rabbit rabbit : rabbits )
        {
            if ( State.RABBIT_OUT_OF_BOUNDS == rabbit.state )
            {
                continue;
            }
            chars.set(
                rabbit.x,
                rabbit.y,
                charForRabbit( rabbit ),
                rabbit.saveState()
            );
        }
    }

    private static char charForRabbit( Rabbit rabbit )
    {
        if(rabbit.dir == RIGHT){
            if(rabbit.type.equals(Rabbit.RabbitType.NORMAL)){
                return 'r';
            } else if (rabbit.type.equals(Rabbit.RabbitType.BOSS)){
                return 'R';
            }
        } else{
            if(rabbit.type.equals(Rabbit.RabbitType.NORMAL)){
                return 'j';
            } else if (rabbit.type.equals(Rabbit.RabbitType.BOSS)){
                return 'J';
            }
        }
        return ' ';
    }
}
