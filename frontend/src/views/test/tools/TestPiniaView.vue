<template>
	<div>
		<h1>Pinia Playground</h1>
    <ol>
      <li>Explored 4 ways to manipulate state</li>
    </ol>
    <div>
      <el-button type="primary" size="default" @click="addOne"
        >+ ADD 1</el-button
      >
      <span> Count from Pinia Store: {{ count }}</span>
    </div>
	</div>
</template>

<script lang="ts" setup>
import { useTestStore } from "@/stores/test";
import { storeToRefs } from "pinia";
import { computed } from "vue";
const testStore = useTestStore();
// const count = computed(() => testStore.count)
const { count } = storeToRefs(testStore);
const addOne = () => {
  // 1. direct state mutation (not recommended - scattered logic outside store)
  // testStore.count++
	// 2. use action to update state
	// testStore.increment();
	// OR
	testStore.setCount(++count.value)
	// 3. use $patch to update (bulk change multiple states)
  // 3.1 object style
	// testStore.$patch({
	// 	count: count.value + 1,
	// });
  // 3.2 function style
  // testStore.$patch((state)=>{
  //   state.count += 1
  // })
  // 4. use $state to replace the state object (not recommended - overwrite entire state)
  // testStore.$state = { count: count.value + 1 }
};
</script>

<style scoped></style>
