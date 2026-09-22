<script setup>
import BackButton from '@/shared/components/BackButton.vue';
import HeaderInfo from '../../shared/components/HeaderInfo.vue';
import Workbench from './component/Workbench.vue';

</script>
<template>
    <div class="page">
        <HeaderInfo></HeaderInfo>
        <Workbench></Workbench>
    </div>
    <BackButton :contraste="true"></BackButton>
</template>
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
</style>