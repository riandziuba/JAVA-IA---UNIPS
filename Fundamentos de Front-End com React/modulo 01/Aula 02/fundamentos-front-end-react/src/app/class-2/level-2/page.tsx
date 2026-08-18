import { CounterGlobal } from "@/components/class-2/CounterGlobal";
import { CounterGlobalValue } from "@/components/class-2/CounterGlobalValue";


const Page = async () => (
  <div>
    <h1 className="text-4xl font-bold">Pagina nível 2</h1>
    <CounterGlobal />
    <CounterGlobalValue />
  </div>
);


export default Page;
