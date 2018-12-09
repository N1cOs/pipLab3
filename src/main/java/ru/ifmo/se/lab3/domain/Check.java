package ru.ifmo.se.lab3.domain;

import javafx.geometry.Point2D;
import lombok.Data;
import ru.ifmo.se.lab3.repository.CheckRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import java.util.Date;

@ManagedBean
@RequestScoped
@Data
@Entity
@Table(name = "results")
public class Check {

    private final static Parabola firstParabola = new Parabola(new Point2D(0.25, 0.2),
            new Point2D(0.5, 0.37), new Point2D(0.35, 0.064));
    private final static Parabola secondParabola = new Parabola(new Point2D(0.26, -0.3476),
            new Point2D(0.58, -0.3511), new Point2D(0.4191, -0.2));
    private final static Parabola thirdParabola = new Parabola(new Point2D(0, -0.4482),
            new Point2D(0.26, -0.3476), new Point2D(0.1522, -0.2445));

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_generator")
    @SequenceGenerator(name = "results_generator", sequenceName = "results_seq", allocationSize = 10)
    private int id;

    @Column(name = "x_value")
    private Double valueOfX;

    @Column(name = "y_value")
    private Double valueOfY;

    @Column(name = "r_value")
    private Double valueOfR;

    @Column(name = "result_date")
    private Date date;

    private Integer result;

    @ManagedProperty("#{oracleRepository}")
    @Transient
    private CheckRepository checkRepository;

    private boolean checkArea(double x, double y, double r){
        x = Math.abs(x);
        firstParabola.setCoefficients(r);
        secondParabola.setCoefficients(r);
        thirdParabola.setCoefficients(r);

        if(x >= 0 && x <= 0.1 * r && y >= 0 && y <= 0.3 * r)
            return true;
        else if(x >= 0.1 * r && x <= 0.2 * r && y >= 0 && y <= x + 0.2 * r)
            return true;
        else if(x >= 0.2 * r && x <= 0.25 * r && y >= 0 && y <= -4 * x + 1.2 * r)
            return true;
        else if(x >= 0.25 * r && x <= 0.5 * r && y >= 0 && y <= firstParabola.getValue(x))
            return true;
        else if(x >= 0.5 * r && x <= r && y >= 0 &&
                y <= Math.sqrt((1 - (x * x) / (r * r)) * 0.182 * r * r))
            return true;
        else if(x <= r && x >= 0.58 * r && y <= 0 &&
                y >= Math.sqrt((1 - (x * x) / (r * r)) * 0.185 * r * r))
            return true;
        else if(x <= 0.58 * r && x >= 0.26 * r && y <=0 && y >= secondParabola.getValue(x))
            return true;
        else if(x <= 0.26 * r && y <=0 && y >= thirdParabola.getValue(x))
            return true;
        return false;
    }

    public boolean check(){
        result = checkArea(valueOfX, valueOfY, valueOfR) ? 1 : 0;
        date = new Date();
        checkRepository.save(this);
        return result == 1;
    }
}
