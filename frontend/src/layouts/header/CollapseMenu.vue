<template>
  <el-icon color="#FFF" size="28" style="cursor: pointer;">
    <component :is="menuStore.isMenuCollapsed ? Fold : Expand" @click="menuStore.toggleMenuCollapse()" />
  </el-icon>
</template>

<script lang="ts" setup>
  import { useMenuStore } from "@/stores/menu";
  import { Expand, Fold } from "@element-plus/icons-vue";
  const menuStore = useMenuStore();

</script>

<style scoped></style>
