package grupo9.arquisoft.migrainetrackingmobile.dtos;

/**
 * Created by Santiago on 5/15/2015.
 */
public class DoctorDTO
{
    //--------------------------
    //Atributos
    //--------------------------

    /**
     * El id del doctor. Este es autogenerado
     */
    private long id;

    /**
     * El nombre de usuario del doctor
     */
    private String username;

    /**
     * La clave del doctor
     */
    private String password;

    /**
     * El nombre del doctor
     */
    private String name;

    //--------------------------
    //Constructor
    //--------------------------

    /**
     * Metodo constructor sin argumentos
     */
    public DoctorDTO()
    {

    }

    //-------------------------
    //Metodos
    //-------------------------

    /**
     * Retorna el id del doctor
     * @return el id del doctor
     */
    public long getId() {
        return id;
    }

    /**
     * Cambia el id del doctor
     * @param id el nuevo id del doctor
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retorna el username del doctor
     * @return el username del doctor
     */
    public String getUsername() {
        return username;
    }

    /**
     * Cambie el username del doctor
     * @param username el nuevo username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retorna la clave del doctor
     * @return la clave del doctor
     */
    public String getPassword() {
        return password;
    }

    /**
     * Cambia la clave del doctor
     * @param password la nueva clave del doctor
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retorna el nombre del doctor
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cambia el nombre del doctor
     * @param name el nuevo nombre del doctor
     */
    public void setName(String name) {
        this.name = name;
    }
}
