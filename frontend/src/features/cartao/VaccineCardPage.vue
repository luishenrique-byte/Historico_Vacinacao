<script setup>
import HeaderInfo from './HeaderInfo.vue'
import VaccineList from './VaccineList.vue';
</script>

<!-- HTML -->
<template>
  <div class="page">
    <HeaderInfo></HeaderInfo>
    <VaccineList></VaccineList>
  </div>
</template>

<!-- CSS -->
<style scoped>
.page{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  gap: 15px;
}
</style>
