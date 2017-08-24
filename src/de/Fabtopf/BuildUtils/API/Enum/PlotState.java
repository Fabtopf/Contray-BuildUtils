package de.Fabtopf.BuildUtils.API.Enum;

/**
 * Created by Fabi on 24.08.2017.
 */
public enum PlotState {

    notRated(false),
    bad(true),
    good(true),
    accepted(true);

    private boolean rated;

    private PlotState(boolean rated) {
        this.rated = rated;
    }

    public boolean getRated() {
        return rated;
    }

}
