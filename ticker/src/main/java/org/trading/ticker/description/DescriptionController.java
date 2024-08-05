package org.trading.ticker.description;

import org.springframework.web.bind.annotation.*;
import org.trading.ticker.common.crud.BaseSymbolController;

@RestController
@RequestMapping("/description")
class DescriptionController extends BaseSymbolController<Description> {

    public DescriptionController(DescriptionService descriptionService) {
        super(descriptionService);
    }

}
