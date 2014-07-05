package de.zsgn.industrialscience.factory.tileentity;

import de.zsgn.industrialscience.util.AbsoluteCoordinate;

public interface IChimneySupport {
    public void addChimney(AbsoluteCoordinate chimneyCoord);

    public void removeChimney(AbsoluteCoordinate chimneyCoord);
}
