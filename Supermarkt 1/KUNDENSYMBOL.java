/**
* Ein Kundensymbol (Kreis) auf einem Fenster.
* Das Fenster wird bei Bedarf generiert.
*
* @author Klaus van Dijkstran und Barbara Leidorn
* @version 1.0
*/

import java.awt.*;

class KUNDENSYMBOL
{
    /** Das Anzeigefenster. */
    private Canvas anzeige;

    /** Anzeigegröße für den Kreis */
    private int groesse = 20;

    /** Interna */
    private OBERFLAECHE.FARBE aktFarbe;

    /**
    * Standardkonstruktor für Objekte der Klasse KUNDESYMBOL.
    * Er erzeugt einen schwarzen Kreis in der linken oberen Ecke des Fensters.
    * Das Fenster wird bei Bedarf angelegt.
    */
    KUNDENSYMBOL()
    {
        anzeige = new Canvas()
                  {
                      /**
                       * Stellt die Figur auf dem Fenster dar.
                       */
                      public void paint(Graphics g)
                      {
                          g.setColor(aktFarbe.AWTFarbe());
                          g.fillOval(0, 0, groesse - 1, groesse - 1);
                          g.setColor(Color.black);
                          g.drawOval(0, 0, groesse - 1, groesse - 1);
                      }

                  };
        aktFarbe = OBERFLAECHE.FARBE.schwarz;
        anzeige.setVisible(true);
        anzeige.setSize(groesse, groesse);
        anzeige.setLocation(0, 20);
        (OBERFLAECHE.FensterGeben()).add(anzeige, 0);
    }

    /**
     * Zeichnet die Figur.
     */
    private void Zeichnen()
    {
        anzeige.invalidate();
        anzeige.repaint();
    }

    /**
     * Setzt die FARBE der Figur.
     * @param neueFarbe die neue FARBE der Figur
     */
    void FarbeSetzen(String neueFarbe)
    {
        if("zufall".equals(neueFarbe))
        {
            aktFarbe = OBERFLAECHE.FARBE.ZufallsFarbeErzeugen();
        }
        else
        {
            try
            {
                aktFarbe = OBERFLAECHE.FARBE.valueOf(aktFarbe.getDeclaringClass(), neueFarbe);
            }
            catch(Exception e)
            {
                aktFarbe = OBERFLAECHE.FARBE.weiss;
            }
        }
        Zeichnen();
    }

    /**
     * Setzt die Position der Figur.
     * @param kassennummer Nummer der Kasse
     * @param kundennummer Warteposition (0 für an Kasse stehend)
     */
    void PositionSetzen(int kassennummer, int kundennummer)
    {
        int x = 450 - groesse - kundennummer * 50;
        int y = 100 * kassennummer + 25 - groesse / 2;

        anzeige.setLocation(x, y);
    }

    /**
     * Setzt die Größe der Figur.
     * @param artikelzahl neue Artikelzahl
     */
    void GroesseSetzen(int artikelzahl)
    {
        groesse = 2 * artikelzahl + 2;
        anzeige.setSize(groesse, groesse);
    }

    /**
     * Entfernt die Figur aus der Anzeige
     */
    public void Entfernen()
    {
        (OBERFLAECHE.FensterGeben()).remove(anzeige);
    }
}
