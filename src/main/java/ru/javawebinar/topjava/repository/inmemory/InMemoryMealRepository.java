package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        if (meal.getUserId() == userId) {
            return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        }

        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(id).getUserId() == userId) {
            return repository.remove(id) != null;
        }

        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(id).getUserId() == userId) {
            return repository.get(id);
        }

        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.values()
                .stream()
                .filter(m -> m.getUserId() == userId)
                .sorted((firstMeal, secondMeal) -> firstMeal.getDateTime().isAfter(secondMeal.getDateTime()) ? 0 : 1)
                .collect(Collectors.toList());
    }
}

