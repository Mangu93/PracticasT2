/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_2;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.Container;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Adrian Portillo
 */
public class Elefantes extends JFrame {

    Color3f white = new Color3f(1f, 1f, 1f);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    public String elefante_resource = "resources/elephav.obj";
    SimpleUniverse universo;
    

    public Elefantes() {
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
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        Scene scene = null;

        try {
            scene = file.load(elefante_resource);
        } catch (FileNotFoundException ex) {
            System.err.println("Fallo en carga elefantes");
            System.exit(ERROR);
        }
        BranchGroup elefante1 = scene.getSceneGroup();
        Transform3D deformar = new Transform3D();
        deformar.setScale(new Vector3d(-0.5, 0.5, 1));
        TransformGroup tg_deformar = new TransformGroup(deformar);
        tg_deformar.addChild(elefante1);
        Transform3D rotar_elefante_izq = new Transform3D();
        rotar_elefante_izq.rotY(Math.PI / 9.5d);
        TransformGroup tg_rotar_elefante = new TransformGroup(rotar_elefante_izq);
        tg_rotar_elefante.addChild(tg_deformar);
        Transform3D colocar_izq = new Transform3D();
        colocar_izq.set(new Vector3f(-0.7f, -0.2f, 0.0f));
        TransformGroup tg_colocar_izq = new TransformGroup(colocar_izq);
        tg_colocar_izq.addChild(tg_rotar_elefante);
        //LUZ
        AmbientLight ambientLightNode = new AmbientLight(white);
        ambientLightNode.setInfluencingBounds(bounds);
        // Set up the directional lights
        Vector3f light1Direction = new Vector3f(-1.0f, 1.0f, -1.0f);
        // light coming from left, up, and back quadrant
        Vector3f light2Direction = new Vector3f(1.0f, 1.0f, 1.0f);
        // light coming from right, up, and front quadrant
        DirectionalLight light1
                = new DirectionalLight(white, light1Direction);
        light1.setInfluencingBounds(bounds);
                DirectionalLight light2
                = new DirectionalLight(white, light2Direction);
        light2.setInfluencingBounds(bounds);
        //objRoot.addChild(ambientLightNode);
        objRoot.addChild(light1);
        objRoot.addChild(light2);
        BranchGroup elf_izq_final = new BranchGroup();
        elf_izq_final.addChild(tg_colocar_izq);
        objRoot.addChild(elf_izq_final);
        return objRoot;

    }

    public static void main(String args[]) {
        Elefantes x = new Elefantes();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("El cazador de elefantes");
        x.setSize(800, 600);
        x.setVisible(true);
    }
}
