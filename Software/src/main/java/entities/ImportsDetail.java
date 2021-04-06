package entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repositories.ImportsRepository;
import repositories.MerchandiseRepository;
import utils.HibernateUtils;

import javax.persistence.*;

@Entity
@Table(name = "imports_detail")
public class ImportsDetail {
    @Id
    @Column(name = "id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "imports_id")
    private Imports imports;
    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "amount")
    private Long amount;

    public ImportsDetail() {
    }

    public ImportsDetail(String id, Imports imports, Merchandise merchandise, int quantity, Long amount) {
        this.id = id;
        this.imports = imports;
        this.merchandise = merchandise;
        this.quantity = quantity;
        this.amount = amount;
    }

    public ImportsDetail(Object o) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session;

        Object[] field = (Object[]) o;
        this.id = (String) field[0];
        session = sessionFactory.openSession();
        this.imports = ImportsRepository.getById(session, (String) field[1]);
        session = sessionFactory.openSession();
        this.merchandise = MerchandiseRepository.getById(session, (String) field[2]);
        this.quantity = Math.toIntExact((Long) field[3]);
        this.amount = (Long) field[4];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Imports getImports() {
        return imports;
    }

    public void setImports(Imports imports) {
        this.imports = imports;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
