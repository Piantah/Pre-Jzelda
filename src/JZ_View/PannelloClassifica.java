package JZ_View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PannelloClassifica extends JPanel {
    private HashMap<String,Integer> classifica;
    private List<String> podio;
    private JButton chiudi = new JButton("Menu");
    private Font mioFont = new Font("Arial", Font.BOLD, 24);

    public PannelloClassifica(){
        setBackground(Color.BLACK);
        chiudi.setBounds(100, 900, 150, 50);
        add(chiudi);
        chiudi.setFocusable(false);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        update();
        System.out.println(podio.size());
        writeClassifica(g);

    }
    private void writeClassifica(Graphics g){
        g.setFont(mioFont);
        g.setColor(Color.WHITE);
        for (int i=0; i< podio.size();i++){
            String str= (1+i)+": "+podio.get(i)+"---"+classifica.get(podio.get(i));
            g.drawString(str,100,(1+i)*100);
        }
    }

    private void update(){
        classifica=new HashMap<>();
        String classPath = "JZ_Saves/" + "classifica" + ".txt";
        File file = new File(classPath);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            //stream per la lettura del file della classifica (è identico a quello del model)
            reader.lines().forEach(riga->{
                String[] parti = riga.split(":");
                classifica.put(parti[0], Integer.valueOf(parti[1]));
            });
            reader.close();
            //Stream per filtrare la classifica
            podio=classifica.entrySet().stream()
                    .sorted((a,b)->b.getValue().compareTo(a.getValue()))
                    .limit(5)
                    .map(valore->valore.getKey())
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // =^.^=
    }

    public void addChiudiListener(ActionListener listener) {
        chiudi.addActionListener(listener);
    }


}
