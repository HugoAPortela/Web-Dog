package br.com.webdog.persistence.model;

import javax.persistence.*;

//@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    private String name;
    private String type;
    private byte pic;

    private Host host;

    public Image() {
    }

    public Image(String name, String type, byte pic, Host host) {
        this.name = name;
        this.type = type;
        this.pic = pic;
        this.host = host;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte getPic() {
        return pic;
    }

    public void setPic(byte pic) {
        this.pic = pic;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }
}
