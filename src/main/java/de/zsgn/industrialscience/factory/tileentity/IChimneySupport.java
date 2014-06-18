package de.zsgn.industrialscience.factory.tileentity;

import de.zsgn.industrialscience.AbsoluteCoordinate;

public interface IChimneySupport {
    public Boolean addChimney(AbsoluteCoordinate chimneyCoord);
    
    public void removeChimney(AbsoluteCoordinate chimneyCoord);
}
