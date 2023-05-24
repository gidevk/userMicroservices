package DOA;

import Entity.Colour;

public interface ColourDAO {
    Colour saveColour(Colour colour);

    Colour getColourById(int Id);
}
