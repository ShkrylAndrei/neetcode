package ScoreOffString;

public class Solution {

    /**
     * Вычисляет score строки как сумму абсолютных разниц ASCII-кодов соседних символов
     * Алгоритм:
     * 1. Проходим по строке от 0 до len-2 (последняя пара: [len-2, len-1])
     * 2. Для каждой пары соседних символов вычисляем |s[i+1] - s[i]|
     * 3. Суммируем все разницы
     * Сложность:
     * - Время: O(n), где n = s.length()
     * - Память: O(1) - только переменная для суммы
     *
     * @param s входная строка (длина >= 2)
     * @return суммарный score строки
     */
    public int scoreOfString(String s) {
        // Защита от некорректного ввода (по условию задачи не требуется, но good practice)
        if (s == null || s.length() < 2) {
            throw new IllegalArgumentException("String length must be at least 2");
        }

        int score = 0;

        // Проходим по всем парам соседних символов
        // Для строки длины n есть (n-1) пар соседей
        for (int i = 0; i < s.length() - 1; i++) {
            // Автоматическое преобразование char → int при вычитании
            int diff = s.charAt(i + 1) - s.charAt(i);

            // Берем абсолютное значение разницы
            score += Math.abs(diff);
        }

        return score;
    }

    // Альтернативная реализация через улучшенный for-each (менее читаема для этой задачи)
    public int scoreOfStringAlternative(String s) {
        int score = 0;
        char prev = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char current = s.charAt(i);
            score += Math.abs(current - prev);
            prev = current;
        }

        return score;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "code";
        int output = solution.scoreOfString(input);
        System.out.println(output);
    }
}
