/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendance;

/**
 *
 * @author ratho
 */
public class StudentDetails {
    private long sapid;
    private String name;

    public StudentDetails(long sapid, String name) {
        this.sapid = sapid;
        this.name = name;
    }
    
    public StudentDetails(){
    }

    public long getSapid() {
        return sapid;
    }

    public String getName() {
        return name;
    }
}