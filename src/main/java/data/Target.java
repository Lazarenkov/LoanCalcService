package data;

import jakarta.persistence.*;
import repo.PaymentRepository;
import util.valueExtractor.ExtractNested;
import util.valueExtractor.ExtractValue;

import java.util.UUID;

@Entity
public class Target {

    @ExtractValue
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ExtractNested
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calc_params_id", nullable = true)
    private CalcParams calcParams;
    @ExtractNested
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calc_result_id", nullable = true)
    private CalcResult calcResult;

    public Target() {
    }

    public Target(CalcParams calcParams) {
        this.calcParams = calcParams;
    }


    public Target(CalcParams calcParams, CalcResult calcResult) {
        this.calcParams = calcParams;
        this.calcResult = calcResult;
    }

    public Target(UUID id, CalcParams calcParams, CalcResult calcResult, PaymentRepository repo) {
        this.id = id;
        this.calcParams = calcParams;
        this.calcResult = calcResult;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CalcParams getCalcParams() {
        return calcParams;
    }

    public void setCalcParams(CalcParams calcParams) {
        this.calcParams = calcParams;
    }

    public CalcResult getCalcResult() {
        return calcResult;
    }

    public void setCalcResult(CalcResult calcResult) {
        this.calcResult = calcResult;
    }
}
