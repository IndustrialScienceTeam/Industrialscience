package de.zsgn.industrialscience.factory.tileentity.controllers;

public interface IEnergyLinkSupport {
    public double getRequestedEnergy();
    public double injectEnergy(double energy);
    public double percentageFilled();
}
