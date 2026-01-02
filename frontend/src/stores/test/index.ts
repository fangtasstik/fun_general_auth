import { defineStore } from 'pinia';
import { ref, computed } from 'vue';

// Optional API
// export const useTestStore = defineStore('testStore', {
//   state: () => {
//     return {
//       count: 0,
//     }
//   },
//   getters: {
//     getCount(state) {
//       return state.count;
//     }
//   },
//   actions: {
//     setCount(newCount: number) {
//       this.count = newCount;
//     }
//   }
// });

// Composition API
export const useTestStore = defineStore('testStore', () => {
  // state
  const count = ref(0);
  // getters
  const doubleCount = computed(() => count.value * 2);
  // actions
  const setCount = (n: number) => {
    count.value = n;
  }
  const increment = () => {
    count.value++;
  }
  const decrement = () => {
    count.value--;
  }

  return { count, doubleCount, setCount, increment, decrement };
});