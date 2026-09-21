<script setup>
import BackButton from '@/shared/components/BackButton.vue';
import HeaderInfo from './HeaderInfo.vue'
import PrintButton from './PrintButton.vue';
import VaccineList from './VaccineList.vue';
</script>

<!-- HTML -->
<template>
  <div class="page">
    <HeaderInfo></HeaderInfo>
    <VaccineList></VaccineList>
  </div>
  <PrintButton></PrintButton>
  <BackButton :contraste="true"></BackButton>
</template>

<!-- CSS -->
<style scoped>
.page{
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  gap: 15px;
}
/* Vaccine Card Page */
@media print{
  .page{
    padding: 30px 0px 0px 0px;
    justify-content: flex-start;
  }
}
</style>
