/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.logicaBaseDeDatos;

//import entity.Pelicula;
import java.util.ArrayList;

/**
 *
 * @author Eduwin
 */
public class Html {

    public String BotonMeGusta(String nickname, int pelicula) {
        String Boton = "<form action=\"CrearCalificacion\" method=\"post\"> "
                + "                <p>"
                + "<tr> "
                + "                <td> "
                + "<tr>"
                + "<input name=\"nickname\" type=\"hidden\" value=\"" + nickname + "\">"
                + "<input name=\"peliculaid\" type=\"hidden\" value=\"" + pelicula + "\">"
                + "        </tr>"
                + "                <input "
                + "                    style='background-color: #3a92d6; /* Blue */\n"
                + "                    border: none;\n"
                + "                    color: white;\n"
                + "                    padding: 15px 32px;\n"
                + "                    text-align: center;\n"
                + "                    text-decoration: none;\n"
                + "                    display: inline-block;\n"
                + "                    font-size: 16px; cursor: pointer;' "
                + "                    type=\"submit\" value=\"Me Gusta\">"
                + "              </td>"
                + "          </tr>"
                + "                </p>"
                + "</form>";
        return Boton;

    }

    public String BotonTegusta(String nickname, int pelicula) {
        String Boton = "<form action=\"EliminarCalificacion\" method=\"post\"> "
                + "                <p>"
                + "<tr> "
                + "                <td> "
                + "<tr>"
                + "<input name=\"nickname\" type=\"hidden\" value=\"" + nickname + "\">"
                + "<input name=\"peliculaid\" type=\"hidden\" value=\"" + pelicula + "\">"
                + "        </tr>"
                + "                <input "
                + "                    style='background-color: #4CAF50; /* Green */\n"
                + "                    border: none;\n"
                + "                    color: white;\n"
                + "                    padding: 15px 32px;\n"
                + "                    text-align: center;\n"
                + "                    text-decoration: none;\n"
                + "                    display: inline-block;\n"
                + "                    font-size: 16px; cursor: pointer;' "
                + "                    type=\"submit\" value=\"Te Gusta ✔\">"
                + "              </td>"
                + "          </tr>"
                + "                </p>"
                + "</form>";
        return Boton;
    }

    public String inicial(String Mensaje) {
        String htmlinicial = "<!DOCTYPE html>\n"
                + "<!--\n"
                + "To change this license header, choose License Headers in Project Properties.\n"
                + "To change this template file, choose Tools | Templates\n"
                + "and open the template in the editor.\n"
                + "-->\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Oscars</title>\n"
                + "<B><FONT SIZE=6 COLOR=\"White\">" + Mensaje + "</FONT>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <link rel=\"stylesheet\" href=\"Estilos/PrincipalPage.css\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"vacio\">\n"
                + "            <div id=\"logo\">\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"contenedor\">  \n"
                + "\n"
                + "            \n"
                + "        <footer>\n"
                + "            <table>\n"
                + "                <td>\n"
                + "                    \n"
                + "                    <form action=\"Registrarse\" method=\"post\">                    \n"
                + "                        <table>\n"
                + "                            <tr>\n"
                + "                                <td class=\"centrar\"><label>Registrarse</label></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td><label>Nickname: </label><input type=\"text\" name=\"nickname\"></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td><label>Correo: </label><input type=\"text\" name=\"correo\"></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td><label>Contraseña: </label><input type=\"password\" name=\"password\"></td>\n"
                + "                                \n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td>\n"
                + "                                <input "
                + "                                    style='background-color: #968926; /* Dorado */\n"
                + "                                    border: none;\n"
                + "                                    color: white;\n"
                + "                                    padding: 15px 32px;\n"
                + "                                    text-align: center;\n"
                + "                                    text-decoration: none;\n"
                + "                                    display: inline-block;\n"
                + "                                    font-size: 16px; cursor: pointer;' "
                + "                                    type=\"submit\" value=\"Registrarme\">\n"
                + "                                </td>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </form>\n"
                + "                </td>   \n"
                + "                \n"
                + "                \n"
                + "                \n"
                + "                \n"
                + "                <td>\n"
                + "                    \n"
                + "                    <form action=\"IniciarSesion\" method=\"post\">                    \n"
                + "                        <table>\n"
                + "                            <tr>\n"
                + "                                <td class=\"centrar\"><label>Iniciar</label>  <br>\n"
                + "                           <label>Sesion</label>\n"
                + "                            </td>\n"
                + "                                        </tr>\n"
                + "                            <tr>\n"
                + "                                <td><label>Nickname: </label><input type=\"text\" name=\"nickname\"></td>\n"
                + "                            </tr>\n"
                + "\n"
                + "                            <tr>\n"
                + "                                <td><label>Contraseña: </label><input type=\"password\" name=\"password\"></td>\n"
                + "                            </tr>\n"
                + "                            <tr>\n"
                + "                                <td>\n"
                + "                                <input "
                + "                                    style='background-color: #968926; /* Dorado */\n"
                + "                                    border: none;\n"
                + "                                    color: white;\n"
                + "                                    padding: 15px 32px;\n"
                + "                                    text-align: center;\n"
                + "                                    text-decoration: none;\n"
                + "                                    display: inline-block;\n"
                + "                                    font-size: 16px; cursor: pointer;' "
                + "                                    type=\"submit\" value=\"Iniciar\">\n"
                + "                                </td>\n"
                + "                            </tr>\n"
                + "                        </table>\n"
                + "                    </form>\n"
                + "                </td> \n"
                + "            </table>\n"
                + "\n"
                + " \n"
                + "        </footer>\n"
                + "            \n"
                + "        \n"
                + "            \n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>";
        return htmlinicial;
    }

    public String pantalla(ArrayList<Integer> peliculasMeGusta, String nickname, int totalPelicula1, int totalPelicula2, int totalPelicula3, int totalPelicula4, int totalPelicula5) {
        String htmlPantalla
                = "<!DOCTYPE html>\n"
                + "<!--\n"
                + "To change this license header, choose License Headers in Project Properties.\n"
                + "To change this template file, choose Tools | Templates\n"
                + "and open the template in the editor.\n"
                + "-->\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Oscars</title>\n"
                + "<form action=\"CerrarSesion\" method=\"post\" style=\"text-align:left\" >"
                + "                <p>"
                + "<tr> "
                + "                <td> "
                + "<tr>"
                + "        </tr>"
                + "                <input "
                + "style='background-color: #968926; /* Dorado */\n"
                + "  border: none;\n"
                + "  color: white;\n"
                + "  padding: 15px 32px;\n"
                + "  text-align: center;\n"
                + "  text-decoration: none;\n"
                + "  display: inline-block;\n"
                + "  font-size: 16px; cursor: pointer;' "
                + " type=\"submit\" value=\"Cerrar Sesion\">"
                + "              </td>"
                + "          </tr>"
                + "                </p>"
                + "</form>"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <link rel=\"stylesheet\" href=\"Estilos/PrincipalPage.css\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div class=\"vacio\">\n"
                + "            <div id=\"logo\">\n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div class=\"contenedor\">  \n"
                + "        <section>\n"
                + "            <h2>\n"
                + "            Bienvenido: \n" + (nickname.toUpperCase())
                + "            </h2><br>\n"
                + "            <h2>\n"
                + "            Mejores Peliculas Nominadas 2020\n"
                + "            </h2>\n"
                + "            </article>\n"
                + "            \n"
                + "            <article>\n"
                + "                <h3>\n"
                + "                Once Upon a Time in Hollywood\n"
                + "                </h3>\n"
                + "                <p id=\"imagen1\">\n"
                + "                </p>\n"
                + "                <h4>\n"
                + "                Sinopsis\n"
                + "                </h4>\n"
                + "                <p class=\"contenido\">\n"
                + "                    En Los Ángeles en 1969, la envejecida estrella de televisión Rick Dalton y su doble de acrobacias Cliff Booth luchan por abrirse camino en una industria y una ciudad que ya casi no reconocen.\n"
                + "                </p>"
                + "                <h3>\n" 
                +"Esta pelicla tiene "+ totalPelicula1+" Me Gusta"
                + "                </h3>\n";

        if (peliculasMeGusta.contains(1)) {
            htmlPantalla += BotonTegusta(nickname, 1);

        } else {
            htmlPantalla += BotonMeGusta(nickname, 1);

        }
        htmlPantalla
                += "            </article>\n"
                + "            \n"
                + "            <article>\n"
                + "                <h3>\n"
                + "                Jojo Rabbit\n"
                + "                </h3>\n"
                + "                <p id=\"imagen2\">\n"
                + "                </p>\n"
                + "                <h4>\n"
                + "                Sinopsis\n"
                + "                </h4>\n"
                + "                <p class=\"contenido\">\n"
                + "                    Durante la Segunda Guerra Mundial, el mundo de un niño alemán solitario se pone patas arriba cuando descubre que su madre soltera esconde a una joven judía en su ático. Ayudado únicamente por su idiota amigo imaginario Adolf Hitler, Jojo debe enfrentarse a su nacionalismo ciego.\n"
                + "                </p>\n"
                + "                <h3>\n" 
                +"Esta pelicla tiene "+ totalPelicula2+" Me Gusta"
                + "                </h3>\n";

        if (peliculasMeGusta.contains(2)) {
            htmlPantalla += BotonTegusta(nickname, 2);

        } else {
            htmlPantalla += BotonMeGusta(nickname, 2);

        }
        htmlPantalla
                += "            </article>\n"
                + "\n"
                + "            <article>\n"
                + "                <h3>\n"
                + "                Marriage Story\n"
                + "                </h3>\n"
                + "                <p id=\"imagen3\">\n"
                + "                </p>\n"
                + "                <h4>\n"
                + "                Sinopsis\n"
                + "                </h4>\n"
                + "                <p class=\"contenido\">\n"
                + "                    Una joven pareja creativa y su hijo navegan por las complicadas aguas de la separación entre dos costas y el inminente divorcio.\n"
                + "                </p>\n"
                + "                <h3>\n" 
                +"Esta pelicla tiene "+ totalPelicula3+" Me Gusta"
                + "                </h3>\n";
        if (peliculasMeGusta.contains(3)) {
            htmlPantalla += BotonTegusta(nickname, 3);

        } else {
            htmlPantalla += BotonMeGusta(nickname, 3);

        }
        htmlPantalla
                += "            </article>    \n"
                + "\n"
                + "            <article>\n"
                + "                <h3>\n"
                + "                The Irishman\n"
                + "                </h3>\n"
                + "                <p id=\"imagen4\">\n"
                + "                </p>\n"
                + "                <h4>\n"
                + "                Sinopsis\n"
                + "                </h4>\n"
                + "                <p class=\"contenido\">\n"
                + "                    De anciano, el veterano de la Segunda Guerra Mundial Frank Sheeran reflexiona sobre su vida como estafador y sicario de la mafia, trabajando junto a muchas figuras notorias, incluido Jimmy Hoffa, el tema de uno de los mayores misterios sin resolver en la historia de Estados Unidos.\n"
                + "                </p>\n"
                + "                <h3>\n" 
                +"Esta pelicla tiene "+ totalPelicula4+" Me Gusta"
                + "                </h3>\n";

        if (peliculasMeGusta.contains(4)) {
            htmlPantalla += BotonTegusta(nickname, 4);

        } else {
            htmlPantalla += BotonMeGusta(nickname, 4);

        }
        htmlPantalla
                += "            </article>\n"
                + "\n"
                + "\n"
                + "            <article>\n"
                + "                <h3>\n"
                + "                Parasite\n"
                + "                </h3>\n"
                + "                <p id=\"imagen5\">\n"
                + "                </p>\n"
                + "                <h4>\n"
                + "                Sinopsis\n"
                + "                </h4>\n"
                + "                <p class=\"contenido\">\n"
                + "                    La codicia, la discriminación de clase y un intruso misterioso amenazan la relación simbiótica recién formada entre la acaudalada familia Park y el indigente clan Kim.\n"
                + "                </p>\n"
                + "                <h3>\n" 
                +"Esta pelicla tiene "+ totalPelicula5+" Me Gusta"
                + "                </h3>\n";

        if (peliculasMeGusta.contains(5)) {
            htmlPantalla += BotonTegusta(nickname, 5);

        } else {
            htmlPantalla += BotonMeGusta(nickname, 5);

        }
        htmlPantalla
                += "            </article>\n"
                + "        </section>\n"
                + "            \n"
                + "        </div>\n"
                + "    </body>\n"
                + "</html>\n";

        return htmlPantalla;

    }
}
