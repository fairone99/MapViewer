import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactory;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;

import javax.swing.*;

/**
 * Created by l0g0s on 9/14/13.
 */
public class Start {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Form mapViewer = new Form();
                mapViewer.setDefaultProvider(Providers.SwingLabsBlueMarble);

                final int max = 17;
                TileFactoryInfo info = new TileFactoryInfo(1, max - 2, max,
                        256, true, true, // tile size is 256 and x/y orientation is normal
                        "http://tile.openstreetmap.org",//5/15/10.png",
                        "x", "y", "z") {
                    public String getTileUrl(int x, int y, int zoom) {
                        zoom = max - zoom;
                        String url = this.baseURL + "/" + zoom + "/" + x + "/" + y + ".png";
                        return url;
                    }

                };
                TileFactory tf = new DefaultTileFactory(info);
                mapViewer.setTileFactory(tf);
                mapViewer.setZoom(14);
                mapViewer.setAddressLocation(new GeoPosition(51.5, 0));
                //mapViewer.getMainMap().setDrawTileBorders(true);
                mapViewer.getMainMap().setRestrictOutsidePanning(true);
                mapViewer.getMainMap().setHorizontalWrapped(false);

                ((DefaultTileFactory) mapViewer.getMainMap().getTileFactory()).setThreadPoolSize(8);
            }
        });
    }
}
