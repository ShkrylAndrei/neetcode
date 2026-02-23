package IsSubsequence;

public class Solution {

    /**
     * Проверяет, является ли s подпоследовательностью t
     * Алгоритм: два указателя
     * - i: индекс в строке s (подпоследовательность)
     * - j: индекс в строке t (исходная строка)
     * Сложность:
     * - Время: O(n + m), где n = s.length(), m = t.length()
     * - Память: O(1)
     *
     * @param s подпоследовательность для проверки
     * @param t исходная строка
     * @return true если s является подпоследовательностью t
     */
    public boolean isSubsequence(String s, String t) {
        // Edge case: пустая строка всегда является подпоследовательностью
        if (s.isEmpty()) {
            return true;
        }

        int i = 0; // Указатель для s
        int j = 0; // Указатель для t

        // Проходим по t пока не найдем все символы s или не дойдем до конца t
        while (j < t.length() && i < s.length()) {
            if (t.charAt(j) == s.charAt(i)) {
                i++; // Нашли символ из s — двигаем указатель s
            }
            j++; // Всегда двигаем указатель t
        }

        // Если дошли до конца s — все символы найдены в правильном порядке
        return i == s.length();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "ace", t = "abcde";
        boolean output = solution.isSubsequence(s,t);
        System.out.println(output);
    }
}
