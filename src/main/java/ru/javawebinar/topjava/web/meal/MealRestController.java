package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.*;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(MealRestController.class);

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public void update(Meal meal, int userId) {
        log.info("meal {}", meal.getId());
        assureIdConsistent(meal, userId);
        service.update(meal, userId);
    }

    public Meal create(Meal meal) {
        log.info("Created meals");
        checkNew(meal);
        return service.create(meal, authUserId());
    }

    public List<MealTo> getAll() {
        log.info("Get all meals");
        return MealsUtil.getTos(service.getAll(authUserId()), authUserCaloriesPerDay());
    }

    public List<MealTo> getBetween(LocalDate startData, LocalDate endData, LocalTime startTime, LocalTime endTime) {
        log.info("Get filtered meals");
        return MealsUtil.getFilteredTos(service.getAll(authUserId()), authUserCaloriesPerDay(), startTime, endTime);
    }

}