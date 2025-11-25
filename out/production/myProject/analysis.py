import pandas as pd
import matplotlib.pyplot as plt

#################################
# KLINE
#################################

# load the datasets
kline_sur_df = pd.read_csv('data/kline_result_with_Surgicalmask.tsv', sep='\t')
kline_sur_df = kline_sur_df.head(20000)

kline_n95_df = pd.read_csv('data/kline_result_with_N95mask.tsv', sep='\t')
kline_n95_df = kline_n95_df.head(20000)

# get the infection counts
kline_surgical_infected=kline_sur_df.infected.tolist()
kline_n95_infected=kline_n95_df.infected.tolist()

plt.figure(figsize=(8, 6), dpi=80)
x = range(len(kline_n95_infected))

plt.plot(x, kline_surgical_infected, label = "with\nsurgical mask",color='blue')
plt.plot(x, kline_n95_infected, label = "with\nn95 mask", color='green')
plt.xlabel('simulation steps', fontsize=20)
plt.ylabel('# people infected', fontsize=20)
plt.title('Location: Kline', fontsize=20)
plt.legend(fontsize=15)
plt.grid()
plt.tight_layout()
# plt.show()
plt.savefig('Kline.png')
#################################
# LAB
#################################
# load the datasets
lab_sur_df = pd.read_csv('data/lab_result_with_Surgicalmask.tsv', sep='\t')
# keep only the furst 20000 steps/rows from the table
lab_sur_df = lab_sur_df.head(20000)


lab_n95_df = pd.read_csv('data/lab_result_with_N95mask.tsv', sep='\t')
lab_n95_df = lab_n95_df.head(20000)

# get the infection counts
lab_surgical_infected=lab_sur_df.infected.tolist()
lab_n95_infected=lab_n95_df.infected.tolist()



# plot lines
plt.figure(figsize=(8, 6), dpi=80)
x = range(len(lab_sur_df))

plt.plot(x, lab_surgical_infected, label = "with\nsurgical mask",color='blue')
plt.plot(x, lab_n95_infected, label = "with\nn95 mask", color='green')
plt.xlabel('simulation steps', fontsize=20)
plt.ylabel('# people infected', fontsize=20)
plt.title('Location: Lab', fontsize=20)
plt.legend(fontsize=15)
plt.grid()
plt.tight_layout()
# plt.show()
plt.savefig('Lab.png')
