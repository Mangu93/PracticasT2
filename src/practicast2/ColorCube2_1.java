/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;

/**
 *
 * @author Adrian Portillo
 */
public class ColorCube2_1 {
    
    public ColorCube2_1() {
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
        rotacion.rotY(Math.PI / 4.0d); //Datos de rotación en Y
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
