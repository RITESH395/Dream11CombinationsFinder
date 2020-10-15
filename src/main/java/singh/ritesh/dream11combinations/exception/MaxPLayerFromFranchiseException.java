package singh.ritesh.dream11combinations.exception;

import singh.ritesh.dream11combinations.bo.Franchise;

public class MaxPLayerFromFranchiseException extends RuntimeException
{
    Franchise franchise;

    public MaxPLayerFromFranchiseException(Franchise franchise,String msg)
    {
        super(msg);
        this.franchise = franchise;
    }
}
