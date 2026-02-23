package IsSubsequence;

import java.util.*;

public class OptimizedSubsequenceChecker {

    // Предварительно обработанные данные для строки t
    private final Map<Character, List<Integer>> charPositions;
    private final int tLength;

    /**
     * Конструктор: один раз обрабатываем строку t для множественных запросов
     * Сложность предварительной обработки:
     * - Время: O(m), где m = t.length()
     * - Память: O(m) — храним все позиции символов
     *
     * @param t исходная строка для построения индекса
     */
    public OptimizedSubsequenceChecker(String t) {
        this.tLength = t.length();
        this.charPositions = new HashMap<>();

        // Строим маппинг: символ → список позиций (в порядке возрастания)
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            charPositions.computeIfAbsent(c, k -> new ArrayList<>()).add(i);
        }
    }

    /**
     * Проверка подпоследовательности с использованием предварительно обработанных данных
     * Алгоритм:
     * 1. Для каждого символа в s ищем его позицию в t после текущей позиции
     * 2. Используем бинарный поиск для быстрого нахождения следующей позиции
     * 3. Если символ не найден или нет позиции после текущей — возвращаем false
     * Сложность на запрос:
     * - Время: O(n × log(m)), где n = s.length(), m = t.length()
     *   (бинарный поиск по списку позиций для каждого символа)
     * - Память: O(1) дополнительной памяти на запрос
     * Для миллиардов запросов:
     * - Общая сложность: O(m + k × n × log(m))
     * - При k = 10⁹, n = 100, m = 10⁴: ~10⁹ × 100 × log₂(10⁴) ≈ 1.3 × 10¹² операций
     *   (в 10 раз быстрее базового подхода!)
     *
     * @param s подпоследовательность для проверки
     * @return true если s является подпоследовательностью t
     */
    public boolean isSubsequence(String s) {
        // Edge case: пустая строка всегда подпоследовательность
        if (s.isEmpty()) {
            return true;
        }

        int currentPosition = -1; // Последняя найденная позиция в t

        for (char c : s.toCharArray()) {
            // Получаем список позиций для символа c
            List<Integer> positions = charPositions.get(c);

            // Символ отсутствует в t — не подпоследовательность
            if (positions == null || positions.isEmpty()) {
                return false;
            }

            // Бинарный поиск: находим минимальную позицию > currentPosition
            int nextPosition = binarySearchNext(positions, currentPosition);

            // Если нет подходящей позиции — не подпоследовательность
            if (nextPosition == -1) {
                return false;
            }

            currentPosition = nextPosition;
        }

        return true;
    }

    /**
     * Бинарный поиск минимальной позиции в списке, которая > target
     * @param positions отсортированный список позиций символа в t
     * @param target текущая позиция (ищем позицию > target)
     * @return минимальная позиция > target или -1 если не найдено
     */
    private int binarySearchNext(List<Integer> positions, int target) {
        int left = 0;
        int right = positions.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (positions.get(mid) > target) {
                // Нашли кандидата, но ищем минимальный
                result = positions.get(mid);
                right = mid - 1;
            } else {
                // Слишком маленькая позиция — ищем правее
                left = mid + 1;
            }
        }

        return result;
    }
}
