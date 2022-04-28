package revagenda.models;



import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;


public class AbstractReimbursement extends Model {

    private int id;
    private double amount;
    private Date submitted;
    private Date resolved;
    private String description;
    private int author;
    private int resolver;
    private int status_id;
    private int type_id;


    public AbstractReimbursement() {
        super();
    }

    public AbstractReimbursement(int id, double amount, Date submitted, String description, int author,int status_id,
        int type_id){
        this.id = id;
        this.amount = amount;
        this.submitted = Date.valueOf(LocalDate.now());
        this.description = description;
        this.author = author;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public AbstractReimbursement(int id, double amount, Date submitted, Date resolved,
                                 String description, int author, int resolver, int status_id, int type_id) {
        this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getResolver() {
        return resolver;
    }

    public void setResolver(int resolver) {
        this.resolver = resolver;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractReimbursement that = (AbstractReimbursement) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && author == that.author && resolver == that.resolver && status_id == that.status_id && type_id == that.type_id && Objects.equals(submitted, that.submitted) && Objects.equals(resolved, that.resolved) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, submitted, resolved, description, author, resolver, status_id, type_id);
    }

    @Override
    public String toString() {
        return "AbstractReimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}
