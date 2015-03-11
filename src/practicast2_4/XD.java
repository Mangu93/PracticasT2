/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_4;

import com.sun.j3d.utils.geometry.Text2D;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import java.awt.Font;
import java.util.Random;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

/**
 *
 * @author Adrian Portillo
 */
public class XD extends JFrame {
    
    SimpleUniverse universo;
    public TransformGroup rotacion_total;
    
    XD() {
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
        
        BranchGroup LAD = new BranchGroup();
        Text2D d = new Text2D("XD", new Color3f(0f, 1f, 0f), "Arial", 72, Font.BOLD);
        PolygonAttributes polyAttrib = new PolygonAttributes();
        polyAttrib.setCullFace(PolygonAttributes.CULL_NONE);
        polyAttrib.setBackFaceNormalFlip(true);
        d.getAppearance().setPolygonAttributes(polyAttrib);
        LAD.addChild(d);
        Transform3D rt = new Transform3D();
        rotacion_total = new TransformGroup(rt);
        rotacion_total.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rotacion_total.addChild(LAD);
        objRoot.addChild(rotacion_total);
        return objRoot;
    }
    
    public static void main(String args[]) {
        XD x = new XD();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("XD");
        x.setSize(800, 600);
        x.setVisible(true);
        Transform3D actual = new Transform3D();
        Transform3D rotacion = new Transform3D();
        Transform3D movimiento = new Transform3D();
        float this_angulo = 0.01f;
        Random rnd = new Random();
        while (true) {
            try {
                
                x.rotacion_total.getTransform(actual);
                rotacion.rotZ(this_angulo);
                movimiento.set(new Vector3d(rnd.nextDouble(),rnd.nextDouble(), rnd.nextDouble()));
                actual.mul(rotacion);
               
                x.rotacion_total.setTransform(actual);
                
                x.rotacion_total.getTransform(actual);
                actual.mul(movimiento);
                Thread.sleep(5);
            } catch (Exception ex) {
            }
        }
    }
}
