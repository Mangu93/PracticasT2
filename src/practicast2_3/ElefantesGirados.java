/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicast2_3;

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
public class ElefantesGirados extends JFrame {

    Color3f white = new Color3f(1f, 1f, 1f);
    BoundingSphere bounds = new BoundingSphere(new Point3d(0, 0, 0), 100.0);
    public String elefante_resource = "resources/elephav.obj";
    SimpleUniverse universo;

    public ElefantesGirados() {
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
        Scene scene1 = null;
        Scene scene2 = null;
        try {
            scene1 = file.load(elefante_resource);
            scene2 = file.load(elefante_resource);
        } catch (FileNotFoundException ex) {
            System.err.println("Fallo en carga elefantes");
            System.exit(ERROR);
        }
        BranchGroup elefante1 = scene1.getSceneGroup();
        BranchGroup elefante2 = scene2.getSceneGroup();
        //Primer elefante
        Transform3D deformar = new Transform3D();
        deformar.setScale(new Vector3d(1, 0.7, 1));
        TransformGroup tg_deformar = new TransformGroup(deformar);
        tg_deformar.addChild(elefante1);
        Transform3D rotar_elefante_izq = new Transform3D();
        rotar_elefante_izq.rotY(Math.PI / 5d);
        TransformGroup tg_rotar_elefante = new TransformGroup(rotar_elefante_izq);
        tg_rotar_elefante.addChild(tg_deformar);
        Transform3D colocar_izq = new Transform3D();
        colocar_izq.set(new Vector3f(-0.5f, 0f, 0.0f));
        TransformGroup tg_colocar_izq = new TransformGroup(colocar_izq);
        tg_colocar_izq.addChild(tg_rotar_elefante);
        //Segundo elefante
        Transform3D colocar_der = new Transform3D();
        colocar_der.set(new Vector3f(0.4f, 0, 0));
        TransformGroup tg_colocar_der = new TransformGroup(colocar_der);
        tg_colocar_der.addChild(elefante2);
        TransformGroup tg_deformar_der = new TransformGroup(deformar);
        tg_deformar_der.addChild(tg_colocar_der);
        //LUZ
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
        objRoot.addChild(light1);
        objRoot.addChild(light2);
        BranchGroup elf_izq_final = new BranchGroup();
        elf_izq_final.addChild(tg_colocar_izq);
        BranchGroup elf_der_final = new BranchGroup();
        elf_der_final.addChild(tg_deformar_der);
        objRoot.addChild(elf_izq_final);
        objRoot.addChild(elf_der_final);
        return objRoot;

    }

    void colocarCamara(SimpleUniverse universo, Point3d posiciónCamara, Point3d objetivoCamara) {
        Point3d posicionCamara = new Point3d(posiciónCamara.x + 0.001, posiciónCamara.y + 0.001d, posiciónCamara.z + 0.001);
        Transform3D datosConfiguracionCamara = new Transform3D();
        datosConfiguracionCamara.lookAt(posicionCamara, objetivoCamara, new Vector3d(0.001, 1.001, 0.001));
        try {
            datosConfiguracionCamara.invert();
            TransformGroup TGcamara = universo.getViewingPlatform().getViewPlatformTransform();
            
            TGcamara.setTransform(datosConfiguracionCamara);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void main(String args[]) {
        ElefantesGirados x = new ElefantesGirados();
        x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        x.setTitle("Elefantes + camara rotada");
        x.setSize(800, 600);
        x.colocarCamara(x.universo, new Point3d(0, 1.8 , -1.1), new Point3d(0,1,0));
        x.setVisible(true);
    }
}
