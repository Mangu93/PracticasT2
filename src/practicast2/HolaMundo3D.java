package practicast2;

import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.geometry.ColorCube;
import javax.vecmath.*;



/**
 *
 * @author Adrian Portillo
 */
public class HolaMundo3D extends JFrame {

    SimpleUniverse universo;

    public HolaMundo3D() {
        Container miPanel = getContentPane();
        Canvas3D zonaDibujo = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        miPanel.add(zonaDibujo);
        universo = new SimpleUniverse(zonaDibujo);
        BranchGroup escena = crearEscena();
        escena.compile();
        universo.getViewingPlatform().setNominalViewingTransform(); //Desde 0,0,0 mueve la ViewPlaform un poco para adelante para ver los objetos
        universo.addBranchGraph(escena);
    }

    BranchGroup crearEscena() {
        BranchGroup objRoot = new BranchGroup();
        Transform3D rotacion = new Transform3D();
        rotacion.rotX(Math.PI / 4.0d); //Datos de rotación en X 45 grados
        //hacer cada transformacion en un transformgroup
        //rotacion.rotZ(Math.PI / 4.0d);
        TransformGroup cuboRotando = new TransformGroup(rotacion);
        cuboRotando.addChild(new ColorCube(0.4));
        objRoot.addChild(cuboRotando);
        return objRoot;
    }

    public static void main(String args[]) {
        HolaMundo3D x = new HolaMundo3D();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Hola");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
