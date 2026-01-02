<template>
	<div class="logo" @click="goHome">
		<img :src="MenuLogo" alt="logo" />
		<span v-if="showLogoSpan" class="logo-title">{{ title }}</span>
	</div>
</template>

<script setup lang="ts">
import MenuLogo from "@/assets/funkey.jpg";
import { ref, watch } from "vue";
import { useMenuStore } from "@/stores/menu";
import { useRouter } from "vue-router";

const title = ref("Funkey Auth");
const menuStore = useMenuStore();
const router = useRouter();
const showLogoSpan = ref(true);
let timer: number | undefined = undefined;

const goHome = () => {
  router.push('/')
};

// this watch better used in business component than global store
watch (
  () => menuStore.isMenuCollapsed,
  (isCollapsed:boolean) => {
    // prevent multiple timers running in short time
    if (timer) clearTimeout(timer);
    if (!isCollapsed) {
      timer = window.setTimeout(() => {
        showLogoSpan.value = true;
      }, 200);
    } else {
      showLogoSpan.value = false;
    }
  },
);

</script>

<style scoped lang="scss">
.logo {
	display: flex;
	width: 100%;
	height: 60px;
	background-color: #2b2f3a;
	text-align: center;
	cursor: pointer;
	align-items: center;
	img {
		height: 36px;
		width: 36px;
    margin-right: 10px;
    margin-left: 10px;
		object-fit: cover;
	}
	.logo-title {
    color: #FFF;
    font-size: 18px;
    font-weight: bold;
  }
}
</style>
