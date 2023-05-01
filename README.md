# VideoPlayer

Language Used: Kotlin

Architectural Pattern: MVVM + Repository Pattern 

Package Structure:

After opening the application package (com.example.videoplayer), we see the following structure:

• ‘repository’ folder: Responsible for providing data to the UI Layer. Currently data is being fetched from local host (using http://10.0.2.2:4000/ for emulator). It contains Models, NetworkSource and repo class. The data layer is reactive. It uses LiveData to hold its state, to which the UI layer listens via ViewModel and updates itself accordingly.

• ‘di’ folder: Contains dependency injection implementation using Hilt.

• ‘ui’ folder: The UI layer of the application. Contains Activity, Fragment, ViewModel, Adapters and ViewHolders etc. The ViewModel interacts with the repository implementation of data layer to fetch the data.

• ‘utils’ folder: Contains helper classes and constants.

• ‘MyApplication’ file: Application file.

TechStack Used:

• Android Jetpack Navigation Component: To navigate between fragments.

• ViewModels: To fetch data from repository layer and provide it to ui layer.

• Coroutines: To handle threading.

• LiveData: To keep persistent and upto date data.

• Room DB: To implement offline cache of data.

• Glide: To load and cache images from URL.

• Hilt: To implement dependency injection.

• DataBinding: To bind data directly with xml views.

• Android Exo player for video playback.
