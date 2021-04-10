package ph.apper.activity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.apper.activity.payload.Activity;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class ActivityController {

    private final List<Activity> activities = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Activity>> getAll() {
        return ResponseEntity.ok(activities);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Activity request) {
        activities.add(request);
        return ResponseEntity.ok().build();
    }

}
